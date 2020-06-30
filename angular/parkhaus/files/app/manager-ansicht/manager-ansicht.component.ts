import {Component, OnInit} from '@angular/core';
import {EinnahmenService} from "./einnahmen.service";
import {HttpHeaders} from "@angular/common/http";

/**
 * @author Patrick Metz
 */
@Component({
  selector: 'app-manager-ansicht',
  templateUrl: './manager-ansicht.component.html',
  styleUrls: ['./manager-ansicht.component.css']
})
export class ManagerAnsichtComponent implements OnInit {
  constructor(private einnahmenService: EinnahmenService) {
  }

  updateIntervall: number = 2500;
  intervall : any;

  tagesEinnahmen: string;
  jahresEinnahmen: string;

  ngOnInit(): void {
    this.intervall = window.setInterval(() => {
      this.holeTagesEinnahmen();
      this.holeJahresEinkommen();
    }, this.updateIntervall);
  }

  holeTagesEinnahmen() {
    this.einnahmenService.gibTagesEinnahmen()
      .subscribe(einnahmen => this.tagesEinnahmen = einnahmen);
  }

  holeJahresEinkommen() {
    this.einnahmenService.gibJahresEinnahmen()
      .subscribe(einnahmen => this.jahresEinnahmen = einnahmen);
  }
}
