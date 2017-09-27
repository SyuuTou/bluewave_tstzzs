package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "test_user")
public class TestUser {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "header_pic")
    private String headerPic;

    @Column(name = "pic_file")
    private byte[] picFile;

    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return header_pic
     */
    public String getHeaderPic() {
        return headerPic;
    }

    /**
     * @param headerPic
     */
    public void setHeaderPic(String headerPic) {
        this.headerPic = headerPic;
    }

    /**
     * @return pic_file
     */
    public byte[] getPicFile() {
        return picFile;
    }

    /**
     * @param picFile
     */
    public void setPicFile(byte[] picFile) {
        this.picFile = picFile;
    }
}