import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebSocketAPIService {

  constructor() {
  }

  webSocketEndPoint: string = "server/ws";
  topic: string = "/queue/" + sessionStorage.getItem("id");
  stompClient: any;

  _connect() {
    console.log("Initialize WebSocket Connection");
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect({}, () => {
      _this.stompClient.subscribe(_this.topic, function (message) {
        _this.onMessageReceived(message);
      });
    }, this.errorCallBack);
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error) {
      console.log("errorCallBack -> " + error)
      setTimeout(() => {
          this._connect();
      }, 5000);
  }

  _disconnect() {
    if(this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  send(message) {
        console.log("calling logout api via web socket");
        this.stompClient.send("/app/caregivers/alert", {}, JSON.stringify(message));
  }

  onMessageReceived(message) {
        console.log("Message Recieved from Server :: " + message);
        alert(JSON.stringify(message.body));
    }

}
