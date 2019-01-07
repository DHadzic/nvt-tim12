import { Component, OnInit } from '@angular/core';
import { EventEmitter,Output } from '@angular/core';
import { TicketService } from 'src/app/services/ticket.service';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.scss']
})
export class TicketsComponent implements OnInit {

  public ticket;
  public price:number;

  @Output()
  changeDisplay:EventEmitter<any> = new EventEmitter();

  constructor(private ticketService:TicketService) {
      this.ticket = {user:"user", type:"ONE_DAY"}
   }

  ngOnInit() {
  }

  openCreation(){
    this.changeDisplay.emit();
  }

  priceDisplay(){
    console.log(this.ticket.type);
    if (this.ticket.type == "ONE_TIME"){
      this.price = 200;
    }else if (this.ticket.type == "ONE_DAY" ){
      this.price = 500;
    }else if (this.ticket.type == "ONE_MONTH" ){
      this.price = 1000;
    }else{
      this.price = 1500;
    }
  }

  create(){
    this.ticketService.create(this.ticket);
  }
}
