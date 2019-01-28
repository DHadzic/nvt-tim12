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


  @Output()
  changeDisplay:EventEmitter<any> = new EventEmitter();

  constructor(private ticketService:TicketService, private authenticationService:AuthenticationService) {
      this.ticket = {user: "", ticketType:"ONE_DAY", transportType:"BUS"};
   }

  ngOnInit() {
    this.getUserTickets();
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
      if (this.ticket.transportType == this.prices[i].transportType &&
          this.ticket.ticketType == this.prices[i].ticketType){
            this.price = this.prices[i].price;
      }
    }
  }
    // }
    // if (this.ticket.transportType == "BUS"){
    //   if (this.ticket.ticketType == "ONE_TIME"){
    //     this.price = 60;
    //   }else if (this.ticket.ticketType == "ONE_DAY" ){
    //     this.price = 130;
    //   }else if (this.ticket.ticketType == "ONE_MONTH" ){
    //     this.price = 1200;
    //   }else{
    //     this.price = 10000;
    //   }
    // }else if (this.ticket.transportType == "TRAM"){
    //   if (this.ticket.ticketType == "ONE_TIME"){
    //     this.price = 50;
    //   }else if (this.ticket.ticketType == "ONE_DAY" ){
    //     this.price = 100;
    //   }else if (this.ticket.ticketType == "ONE_MONTH" ){
    //     this.price = 1000;
    //   }else{
    //     this.price = 8000;
    //   }
    // }else {
    //   if (this.ticket.ticketType == "ONE_TIME"){
    //     this.price = 50;
    //   }else if (this.ticket.ticketType == "ONE_DAY" ){
    //     this.price = 120;
    //   }else if (this.ticket.ticketType == "ONE_MONTH" ){
    //     this.price = 1100;
    //   }else{
    //     this.price = 7000;
    //   }
  //   }
  // }

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


}
