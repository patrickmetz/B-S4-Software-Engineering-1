import {Component, OnInit} from '@angular/core';
import {PreisIF} from "../preisIF";
import {PreisService} from "./preis.service";

/**
 * @author Patrick Metz
 */
@Component({
  selector: 'app-preisformular',
  templateUrl: './preisformular.component.html',
  styleUrls: ['./preisformular.component.css']
})
export class PreisformularComponent implements OnInit {
  preise: PreisIF[];
  bestaetigungSichtbar: boolean = false;
  private timeout : any;

  constructor(private preisService: PreisService) {
  }

  ngOnInit() {
    this.holePreise();
  }

  holePreise(): void {
    this.preisService.gibPreise()
      .subscribe(preise => this.preise = preise);
  }

  speicherePreise(): void {
    this.preisService.speicherePreise(this.preise)
      .subscribe(
        preise => this.verarbeitePreise(preise)
      );
  }

  private verarbeitePreise(preise: PreisIF[]) {
    this.preise = preise;
    this.zeigeBestaetigung();
  }

  private zeigeBestaetigung() {
    this.bestaetigungSichtbar = true

    clearTimeout(this.timeout);

    this.timeout = setTimeout(() => {
      this.bestaetigungSichtbar = false
    }, 2000)
  }
}
