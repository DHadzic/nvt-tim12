import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-line',
  templateUrl: './delete-line.component.html',
  styleUrls: ['./delete-line.component.scss']
})
export class DeleteLineComponent implements OnInit {
  
  public lines;
  public selectedLine;

  constructor() {
    this.lines = [{
      id : 1,
      name : "Prva stanica"
    },
    {
      id : 2,
      name : "Druga stanica"
    }];

    this.selectedLine = {
      id : "",
      name : ""
    };

  }

  ngOnInit() {
  }

  displayLine(id){
    for(let line of this.lines){
      if(line.id == id){
        this.selectedLine = line;
        break;
      }
    }
  }

}
