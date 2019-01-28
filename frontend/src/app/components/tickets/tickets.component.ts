import { Component, OnInit } from '@angular/core';
import { EventEmitter,Output } from '@angular/core';
import { TicketService } from 'src/app/services/ticket.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.scss']
})
export class TicketsComponent implements OnInit {

  public ticket;
  public price:number;
  buyTicketForm:boolean = false;
  private currUser;
  private userTickets;
  public prices;
  userDiscount = 0;


  @Output()
  changeDisplay:EventEmitter<any> = new EventEmitter();

  constructor(private ticketService:TicketService, private authenticationService:AuthenticationService) {
      this.ticket = {user: "", ticketType:"ONE_DAY", transportType:"BUS"};
   }

  ngOnInit() {
    this.getUserTickets();
    this.getUserDiscount();
    this.getPrices();
    this.ticket.user = this.currUser.username;
  }

  openCreation(){
    this.changeDisplay.emit();
  }

  priceDisplay(){
    console.log(this.ticket.ticketType);
    console.log(this.prices);
    for (let i = 0; i < this.prices.length; i++){
      this.price[i].price = this.discountPrice(this.price[i].price);
      if (this.ticket.transportType == this.prices[i].transportType &&
          this.ticket.ticketType == this.prices[i].ticketType){
            this.price = this.prices[i].price;
      }
    }
  }

  discountPrice(price){
    if (this.userDiscount == 30) {
      price = price - price/100*30;
    }else if (this.userDiscount == 25){
      price = price - price/100*25;
    }
    return price;
  }

  create(){
    this.ticketService.create(this.ticket);
  }

  toggleBuy(){
    this.buyTicketForm = !this.buyTicketForm;
  }

  getUserTickets(){
    this.currUser = this.authenticationService.getCurrentUser();
    this.ticketService.getTickets(this.currUser.username).subscribe((tickets) => {
      console.log(tickets);
      this.userTickets = tickets;
    });
  }

  getPrices(){
    this.ticketService.getPrices().subscribe((prices) => {
      this.prices = prices;
      console.log(this.prices);
    }) 
  }

  getUserDiscount(){
    this.ticketService.checkUserDiscount(this.currUser.username).subscribe(success => {
      if (success == "2"){
        this.userDiscount = 30;
      }else if (success == "1"){
        this.userDiscount = 25;
      }else{
        this.userDiscount = 0;
      }
    })
  }


}
