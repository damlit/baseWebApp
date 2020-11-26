import { CurrencyPipe } from '@angular/common';
import { Component, ɵCurrencyIndex } from '@angular/core';
import { DataService } from '../app/data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  newCurrencyName: string;
  newCurrencyValue: string;
  choosenCurrencyName: string;
  choosenCurrencyValue: string;
  currencies: Array<string> = [];

  constructor(private dataService: DataService) {
    this.getCurrenciesNames();
  }

  addCurrency() {
    if (this.isCorrectCurrencyName(this.newCurrencyName) && this.isNumberValue(this.newCurrencyValue)) {
      this.dataService.sendPostRequest(this.newCurrencyName.toUpperCase(), this.newCurrencyValue).subscribe((response) => { });
    } else if (this.isNumberValue(this.newCurrencyValue)) {
      window.alert('Nazwa waluty musi składać się z dokładnie trzech liter!');
    } else {
      window.alert('Wartość waluty musi składać się z cyfr i kropki/przecinka!');
    }
    window.setTimeout( to => { this.getCurrenciesNames(); }, 300);
  }

  isCorrectCurrencyName(currencyName: string) {
    return currencyName.length === 3 && /^[a-zA-Z]+$/.test(currencyName);
  }

  isNumberValue(currencyValue: any) {
    return /^[0-9,.]*$/.test(currencyValue);
  }

  getCurrenciesNames() {
    this.currencies = [];
    this.dataService.sendGetAllRequest().subscribe((data: any[]) => {
      data.forEach(currency => {
        this.currencies.push(currency.name);
      });
    });
  }

  getCurrency(currencyName: string) {
    this.choosenCurrencyName = currencyName;
    this.dataService.sendGetOneRequest(currencyName).subscribe((currency: any) => {
      this.choosenCurrencyValue = currency.currency_value;
    });
  }

  removeCurrency(currencyName: string) {
    this.dataService.sendDeleteRequest(currencyName).subscribe((response) => { });
    window.setTimeout( to => { this.getCurrenciesNames(); }, 300);
  }
}

