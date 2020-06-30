import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PreisformularComponent } from './preisformular/preisformular.component';

import { HttpClientModule }    from '@angular/common/http';
import {FormsModule} from "@angular/forms";
import { ManagerAnsichtComponent } from './manager-ansicht/manager-ansicht.component';

@NgModule({
  declarations: [
    AppComponent,
    PreisformularComponent,
    ManagerAnsichtComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
