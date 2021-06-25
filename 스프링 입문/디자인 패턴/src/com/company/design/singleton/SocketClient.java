package com.company.design.singleton;

/**
 * singleton 핵심 :
 * 1. 기본 생성자를 private로 막음
 * 2. static 메소드를 통해 getInstance()를 만듬
 * 3. 자신의 객체가 null이면 신규 생성, 아니면 바로 자신 객체 리턴
 */
public class SocketClient {

    private static SocketClient socketClient = null;

    // 기본 생성자를 private로 막음
    private SocketClient() {

    }

    public static SocketClient getInstance() {

        // 없는 경우에는 새로 생성
        if(socketClient == null) {
            socketClient = new SocketClient();
        }
        return socketClient;

    }

    public void connect() {
        System.out.println("connect");
    }

}
