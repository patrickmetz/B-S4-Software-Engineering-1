import { Component, OnInit } from '@angular/core';
import {PreisIF} from "../preisIF";
import {PreisService} from "./preis.service";

@Component({
  selector: 'app-preisformular',
  templateUrl: './preisformular.component.html',
  styleUrls: ['./preisformular.component.css']
})
export class PreisformularComponent implements OnInit {
  preise: PreisIF[];

  constructor(private preisService: PreisService) { }

  ngOnInit() {
    this.holePreise();
  }

  holePreise(): void {
    this.preisService.gibPreise()
      .subscribe(preise => this.preise = preise);
  }

  speicherePreise(): void {
    this.preisService.speicherePreise(this.preise)
      .subscribe(preise => this.preise = preise);
  }

}
