import { Component, OnInit } from '@angular/core';
import { PricelistService } from 'src/app/services/pricelist.service';

@Component({
  selector: 'app-manage-pricelist',
  templateUrl: './manage-pricelist.component.html',
  styleUrls: ['./manage-pricelist.component.scss']
})
export class ManagePricelistComponent implements OnInit {
  createPricelistForm:boolean = false;
  prices:any;

  constructor(private pricelistService:PricelistService) { }

  ngOnInit() {
    this.definePrices();
  }

  toggleCreate(){
    this.createPricelistForm = !this.createPricelistForm;
  }

  definePrices(){
    this.prices = [
      {ticketType: "ONE_TIME", transportType: "BUS", price:0},
      {ticketType: "ONE_DAY", transportType: "BUS", price:0},
      {ticketType: "MONTHLY", transportType: "BUS", price:0},
      {ticketType: "YEARLY", transportType: "BUS", price:0},
      {ticketType: "ONE_TIME", transportType: "TRAM", price:0},
      {ticketType: "ONE_DAY", transportType: "TRAM", price:0},
      {ticketType: "MONTHLY", transportType: "TRAM", price:0},
      {ticketType: "YEARLY", transportType: "TRAM", price:0},
      {ticketType: "ONE_TIME", transportType: "TROLLEYBUS", price:0},
      {ticketType: "ONE_DAY", transportType: "TROLLEYBUS", price:0},
      {ticketType: "MONTHLY", transportType: "TROLLEYBUS", price:0},
      {ticketType: "YEARLY", transportType: "TROLLEYBUS", price:0},
    ]
  }

  create(){
    this.pricelistService.create(this.prices).subscribe(success => {
      console.log(success);
    },
    error => {console.log(error);});
  }

  listAll(){
    this.pricelistService.getPricelists().subscribe();
  }

  deletePricelist(pricelistId){
    this.pricelistService.deletePricelist(pricelistId).subscribe();
  }

}
