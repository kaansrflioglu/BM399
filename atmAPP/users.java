package com.example.atmapp;

class users {
    private final String userName;
    private final String userSurname;
    private final String userID;
    private final String userPassword;

    private float userBalance;


    public users(String name, String surname, String id, String password, float balance) {
        this.userName = name;
        this.userSurname = surname;
        this.userID = id;
        this.userPassword = password;
        this.userBalance = balance;
    }
    public String getName() {
        return this.userName;
    }

    public String getSurname() {
        return this.userSurname;
    }

    public String getId() {
        return this.userID;
    }

    public String getPassword() {
        return this.userPassword;
    }

    public float getBalance() {
        return this.userBalance;
    }

    public void setBalance(float balance) {
        this.userBalance = balance;
    }

    static users User1 = new users("Nihat", "Topçuoğlu", "10000000000", "0000", 10000);
    static users User2 = new users("Kadı", "Eronat", "10000000001", "1111", 5000);
    static users User3 = new users("Hüma", "Özbey", "10000000002", "2222", 5000);
    static users User4 = new users("Temel", "Koçyiğit", "10000000003", "3333", 2500);
    static users User5 = new users("Anıl", "Erginsoy", "10000000004", "4444", 1000);
}
