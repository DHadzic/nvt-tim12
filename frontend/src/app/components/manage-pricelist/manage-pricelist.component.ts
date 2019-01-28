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
  pricelists;
  pricelistPrices = [];

  constructor(private pricelistService:PricelistService) { }

  ngOnInit() {
    this.definePrices();
    this.listAll();
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
      alert("Created successfully.");
      location.reload();
    },
    error => {console.log(error);});
  }

  listAll(){
    this.pricelistService.getPricelists().subscribe(
      (pricelists) => {
        this.pricelists = pricelists;
        console.log(this.pricelists); 
        for (let i = 0; i < this.pricelists.length; i++){
          this.listPricelistPrices(this.pricelists[i].id, i);
        }
      }
    );
  }

  listPricelistPrices(pricelistId, i){
    this.pricelistService.getPricelistPrices(pricelistId).subscribe(
      (prices) => {
        this.pricelistPrices.push(prices);
        // console.log(prices);
      }
    )

  }

  deletePricelist(pricelistId){
    this.pricelistService.deletePricelist(pricelistId).subscribe(success => {
      console.log(success);
      if (success == "Pricelist deleted."){
        alert("Deleted successfully.");
        location.reload();
      }else{
        alert(success);
      }
    });
  }

  reactivatePRicelist(pricelistId){
    this.pricelistService.reactivatePricelist(pricelistId).subscribe(success => {
      console.log(success);
      alert("Reactivated successfully.");
      location.reload();
    });
  }

}
