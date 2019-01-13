import { Component, OnInit } from '@angular/core';
import { TicketService } from 'src/app/services/ticket.service'

@Component({
  selector: 'app-validate',
  templateUrl: './validate.component.html',
  styleUrls: ['./validate.component.scss']
})
export class ValidateComponent implements OnInit {

  public transportType:string;
  public username:string;

  constructor(private ticketService:TicketService) {

  }

  ngOnInit() {
  }

  searchForTicket(){
    console.log('search');
    this.ticketService.getPassengerTicket(this.username, this.transportType).subscribe((ticket) => {
      console.log(ticket);
    });
  }

}
