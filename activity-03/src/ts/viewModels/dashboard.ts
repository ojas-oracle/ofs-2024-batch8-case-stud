/**
 * @license
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates.
 * Licensed under The Universal Permissive License (UPL), Version 1.0
 * as shown at https://oss.oracle.com/licenses/upl/
 * @ignore
 */
import * as ko from "knockout";
import * as Bootstrap from "ojs/ojbootstrap";
import Message = require("ojs/ojmessaging");
import "oj-c/input-text";
import "ojs/ojknockout";
import "ojs/ojlabel";
import "ojs/ojbutton";
import "ojs/ojformlayout";
import "oj-c/text-area";
import "oj-c/input-password";
import 'ojs/ojdatetimepicker';
import { ojButton } from "ojs/ojbutton";
import "ojs/ojbutton";
import ArrayDataProvider = require('ojs/ojarraydataprovider');
import { ojMessage } from 'ojs/ojmessage';
import 'ojs/ojmessages';
import MutableArrayDataProvider = require("ojs/ojmutablearraydataprovider");

class DashboardViewModel {

  messages: ojMessage.Message[];
  messagesDataprovider: MutableArrayDataProvider<null, ojMessage.Message>;
  
    constructor() {
      const isoTimeNow = new Date().toISOString();
      //const isoTimeYesterday = new Date(Date.now() - 24 * 60 * 60 * 1000).toISOString();
      this.messages = [
        {
          severity: 'error',
          summary: 'Error message summary',
          detail: 'Error message detail',
          timestamp: isoTimeNow
        }
      ];
      this.messagesDataprovider = new MutableArrayDataProvider(this.messages);
    }

    public buttonAction = (event: ojButton.ojAction) => {
      this.messages = [];
      this.messagesDataprovider = new MutableArrayDataProvider(this.messages)
      console.log("button clicked")
      return true;
    };
}

Bootstrap.whenDocumentReady().then(() => {
  ko.applyBindings(new DashboardViewModel(), document.getElementById("div1"));
});

export = DashboardViewModel;
