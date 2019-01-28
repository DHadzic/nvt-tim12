import { Component, OnInit } from '@angular/core';
import { DeleteVehicleService } from 'src/app/services/delete-vehicle.service';

@Component({
  selector: 'app-delete-vehicle',
  templateUrl: './delete-vehicle.component.html',
  styleUrls: ['./delete-vehicle.component.scss']
})
export class DeleteVehicleComponent implements OnInit {

  public vehicles;
  constructor(private deleteVehicleService:DeleteVehicleService) { 
    this.vehicles = [];
  }

  ngOnInit() {
    this.deleteVehicleService.getVehicles().subscribe(success => {this.setVehicles(success)});
  }

  setVehicles(data){
    console.log(data);
    this.vehicles = data;
  }

  deleteVehicle(w){
      var a = this.vehicles.indexOf(w);
      this.vehicles.splice(a,1);
      this.deleteVehicleService.delete(w).subscribe(success => {console.log(success);},
      error => {console.log(error);});
    
  }
}
