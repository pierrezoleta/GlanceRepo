package com.example.snap.RecyclerViewFollow;
//nikoru
import java.sql.Struct;

import kotlin.contracts.Returns;

public class UsersObject {

    private String email;
    private String uid;

    public UsersObject(String email, String uid){
        this.email = email;
        this.uid = uid;
    }
    public String getUid() { return uid; }
        public void setUid(String uid) {this.uid = uid; }
    public String getEmail() { return email; }
    public void setEmail(String email) {this.email = email; }
}
