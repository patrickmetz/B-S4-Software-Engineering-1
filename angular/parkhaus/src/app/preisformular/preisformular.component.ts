import { Component, OnInit } from '@angular/core';
import {PreisIF} from "../preisIF";

@Component({
  selector: 'app-preisformular',
  templateUrl: './preisformular.component.html',
  styleUrls: ['./preisformular.component.css']
})
export class PreisformularComponent implements OnInit {
  preise: PreisIF[];

  constructor() { }

  ngOnInit(): void {
  }

}
