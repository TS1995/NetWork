package com.moyou.locationdemo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23 0023.
 */

public class LikesBean extends BaseBean {

    /**
     * code : 1
     * message : 成功
     * data : [{"interestUserIsd":42,"avatar":"http://myimagetest.immouo.com/user/avatar/2bff967b-6831-4eb2-b95e-8daf76ed53ef.jpg","nickname":":-D","sig":"","state":"0"}]
     */

    private List<Like> data;


    public List<Like> getData() {
        return data;
    }

    public void setData(List<Like> data) {
        this.data = data;
    }

    public static class Like {
        /**
         * interestUserIsd : 42
         * avatar : http://myimagetest.immouo.com/user/avatar/2bff967b-6831-4eb2-b95e-8daf76ed53ef.jpg
         * nickname : :-D
         * sig :
         * state : 0
         */


        private int interestUserIsd;
        private String avatar;
        private String nickname;
        private String sig;
        private int state;

        public int getInterestUserIsd() {
            return interestUserIsd;
        }

        public void setInterestUserIsd(int interestUserIsd) {
            this.interestUserIsd = interestUserIsd;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSig() {
            return sig;
        }

        public void setSig(String sig) {
            this.sig = sig;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return "Like{" +
                    "interestUserIsd=" + interestUserIsd +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", sig='" + sig + '\'' +
                    ", state=" + state +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LikesBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
