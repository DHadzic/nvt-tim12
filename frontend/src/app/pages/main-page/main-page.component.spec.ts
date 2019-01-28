import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MapService } from 'src/app/services/map.service';

import { MainPageComponent } from './main-page.component';
import { AgmCoreModule } from '@agm/core';
import { AgmDirectionModule } from 'agm-direction';
import { componentNeedsResolution } from '@angular/core/src/metadata/resource_loading';

fdescribe('MainPageComponent', () => {
  let component: MainPageComponent;
  let fixture: ComponentFixture<MainPageComponent>;
  let mapService: any;
  let router: any;

  beforeEach(async(() => {
    let mapService = {
      getLinesPerType : jasmine.createSpy('getLinesPerType').and.returnValue(of({
        "line": {
            "id": 999,
            "name": "8a",
            "stations": [
                {
                    "id": 999,
                    "lat": "45.264054514190796",
                    "lng": "19.83022916394043"
                },
                {
                    "id": 998,
                    "lat": "45.26042973161276",
                    "lng": "19.832632423217774"
                },
                {
                    "id": 997,
                    "lat": "45.25148761176708",
                    "lng": "19.837610603149415"
                },
                {
                    "id": 996,
                    "lat": "45.25252355951289",
                    "lng": "19.847634710688453"
                }
            ]
        },
        "atStations": [
            1
        ]
    })),
      getLineInfo : jasmine.createSpy('getLineInfo').and.returnValue(of({
        "tramLines": [
            "8a"
        ],
        "busLines": [
            "8a",
            "8b"
        ],
        "trolleybusLines": []
      }))
    }

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports : [ FormsModule, AgmCoreModule.forRoot({
        apiKey: 'AIzaSyBG-Cyqxezk9fYYllu_VmEe8q3ZBFf30Yc'
      }) , AgmDirectionModule],
      declarations: [ MainPageComponent ],
      providers: [ { provide: MapService , useValue: mapService},
                  { provide: Router , useValue: routerMock} ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainPageComponent);
    component = fixture.componentInstance;
    mapService = TestBed.get(MapService);
    router = TestBed.get(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load data on init',()=>{
    expect(mapService.getLinesPerType).toHaveBeenCalledTimes(1);
  });

  it('should call service if good data',()=>{
    component.selectedType = "Bus";
    component.selectedLineBus = "Line1";
    
    component.displayLine();

    expect(mapService.getLineInfo).toHaveBeenCalled();
  });

  it('should call service if good data2',()=>{
    component.selectedType = "Tram";
    component.selectedLineTram = "Line1";
    
    component.displayLine();

    expect(mapService.getLineInfo).toHaveBeenCalled();
  });

  it('should call service if good data3',()=>{
    component.selectedType = "Trolleybus";
    component.selectedLineTrolleybus = "Line1";
    
    component.displayLine();

    expect(mapService.getLineInfo).toHaveBeenCalled();
  });

  it('should not call service if bad data',()=>{
    component.selectedType = "Trolleybus";
    component.selectedLineBus = undefined;
    
    component.displayLine();

    expect(mapService.getLineInfo).not.toHaveBeenCalled();
  });

});
