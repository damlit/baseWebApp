package com.currency.currencyservice.model.dao;

import com.currency.currencyservice.model.entity.Currency;
import com.currency.currencyservice.model.HibernateFactory;
import com.currency.currencyservice.service.exception.CurrencyNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CurrencyDao {

    private static final String GET_ALL = "FROM Currency";
    private static final String GET_BY_NAME = "FROM Currency WHERE name=:name";

    public List<Currency> getAll() {
        Session session = HibernateFactory.getInstance().getSessionFactory().openSession();
        List<Currency> currency = session.createQuery(GET_ALL).list();
        session.close();
        return currency;
    }

    public Optional<Currency> get(String name) {
        Session session = HibernateFactory.getInstance().getSessionFactory().openSession();
        Query query = session.createQuery(GET_BY_NAME);
        query.setParameter("name", name);
        Currency currency = (Currency) query.uniqueResult();
        session.close();

        if (currency == null) {
            return Optional.empty();
        }
        return Optional.of(Objects.requireNonNull(currency));
    }

    public Currency save(Currency currency) {
        Session session = HibernateFactory.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.save(currency);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
        return currency;
    }

    public Currency delete(String name) {
        return get(name)
                .map(this::delete)
                .orElseThrow(() -> new CurrencyNotFoundException(name));
    }

    private Currency delete(Currency currencyByName) {
        Session session = HibernateFactory.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.delete(currencyByName);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
        session.close();
        return currencyByName;
    }
}
