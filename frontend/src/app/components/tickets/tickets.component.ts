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

  create(){
    this.ticketService.create(this.ticket);
  }
}
