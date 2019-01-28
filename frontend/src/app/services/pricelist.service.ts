import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PricelistService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  create(pricelistItems){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post("api/pricelist/create",pricelistItems,{headers, responseType: 'text'});
  }

  getPricelists(){
    return this.http.get("api/pricelist/getAll", {responseType: 'json'});
  }

  getPricelistPrices(pricelistId){
    return this.http.get("api/pricelist/getPricelistItems/"+pricelistId, {responseType: 'json'});
  }

  deletePricelist(pricelistId){
    return this.http.post("api/pricelist/deletePricelist/"+pricelistId,{},{responseType: 'text'});
  }

  reactivatePricelist(pricelistId){
    return this.http.post("api/pricelist/reactivatePricelist/"+pricelistId,{},{responseType: 'text'});
  }
}
