package com.zhaorou.zrapplication.user.model;

import java.util.List;

public class UserInfoModel {


    /**
     * data : {"user":{"id":8,"parent_unionid":"0","openid":"onW_y1HkMejN7cvQjasd44vnw88k","unionid":"oqR1C1j8K1rjF5qE_Uj0QA7xVRE4","subscribe":null,"nickname":"无理科","sex":1,"city":"厦门","country":"中国","province":"福建","language":"zh_CN","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLib0bejkecMx4T2jt15wQStw2TtSLlq89SL3DfsqJ1MG5tMSVP2ibDViaib7sqy6MMWZr9gUXFiaUiciaxg/132","subscribe_time":null,"privilege":"","remark":null,"groupid":null,"tagid_list":null,"subscribe_scene":null,"withdraw_money":0,"create_time":"2018-07-13 21:22:16","pid":"mm_51060333_22584073_74748353","adopt_friendpop_num":1,"total_friendpop_num":24,"add_goods_num":12,"password":"a123456","telephone":"13877799665","score":6400,"tao_session":"70002100b0051ef99ae644996938ed8bec6d8c6d503b55478aa212d3bed4a8d508d7185415365139","tao_session_valid_time":null,"tkl_type":"1","is_delete":{"type":"Buffer","data":[0]},"rate":1,"unread_msg_count":14,"withdraw_apply":0}}
     * code : 200
     * msg : success
     */

    private DataBean data;
    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * user : {"id":8,"parent_unionid":"0","openid":"onW_y1HkMejN7cvQjasd44vnw88k","unionid":"oqR1C1j8K1rjF5qE_Uj0QA7xVRE4","subscribe":null,"nickname":"无理科","sex":1,"city":"厦门","country":"中国","province":"福建","language":"zh_CN","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLib0bejkecMx4T2jt15wQStw2TtSLlq89SL3DfsqJ1MG5tMSVP2ibDViaib7sqy6MMWZr9gUXFiaUiciaxg/132","subscribe_time":null,"privilege":"","remark":null,"groupid":null,"tagid_list":null,"subscribe_scene":null,"withdraw_money":0,"create_time":"2018-07-13 21:22:16","pid":"mm_51060333_22584073_74748353","adopt_friendpop_num":1,"total_friendpop_num":24,"add_goods_num":12,"password":"a123456","telephone":"13877799665","score":6400,"tao_session":"70002100b0051ef99ae644996938ed8bec6d8c6d503b55478aa212d3bed4a8d508d7185415365139","tao_session_valid_time":null,"tkl_type":"1","is_delete":{"type":"Buffer","data":[0]},"rate":1,"unread_msg_count":14,"withdraw_apply":0}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 8
             * parent_unionid : 0
             * openid : onW_y1HkMejN7cvQjasd44vnw88k
             * unionid : oqR1C1j8K1rjF5qE_Uj0QA7xVRE4
             * subscribe : null
             * nickname : 无理科
             * sex : 1
             * city : 厦门
             * country : 中国
             * province : 福建
             * language : zh_CN
             * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLib0bejkecMx4T2jt15wQStw2TtSLlq89SL3DfsqJ1MG5tMSVP2ibDViaib7sqy6MMWZr9gUXFiaUiciaxg/132
             * subscribe_time : null
             * privilege :
             * remark : null
             * groupid : null
             * tagid_list : null
             * subscribe_scene : null
             * withdraw_money : 0
             * create_time : 2018-07-13 21:22:16
             * pid : mm_51060333_22584073_74748353
             * adopt_friendpop_num : 1
             * total_friendpop_num : 24
             * add_goods_num : 12
             * password : a123456
             * telephone : 13877799665
             * score : 6400
             * tao_session : 70002100b0051ef99ae644996938ed8bec6d8c6d503b55478aa212d3bed4a8d508d7185415365139
             * tao_session_valid_time : null
             * tkl_type : 1
             * is_delete : {"type":"Buffer","data":[0]}
             * rate : 1
             * unread_msg_count : 14
             * withdraw_apply : 0
             */

            private int id;
            private String parent_unionid;
            private String openid;
            private String unionid;
            private Object subscribe;
            private String nickname;
            private int sex;
            private String city;
            private String country;
            private String province;
            private String language;
            private String headimgurl;
            private Object subscribe_time;
            private String privilege;
            private Object remark;
            private Object groupid;
            private Object tagid_list;
            private Object subscribe_scene;
            private String withdraw_money;
            private String create_time;
            private String pid;
            private int adopt_friendpop_num;
            private int total_friendpop_num;
            private int add_goods_num;
            private String password;
            private String telephone;
            private int score;
            private String tao_session;
            private Object tao_session_valid_time;
            private String tkl_type;
            private IsDeleteBean is_delete;
            private int rate;
            private String unread_msg_count;
            private int withdraw_apply;

            public String getPop_end() {
                return pop_end;
            }

            public void setPop_end(String pop_end) {
                this.pop_end = pop_end;
            }

            private String pop_end;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getParent_unionid() {
                return parent_unionid;
            }

            public void setParent_unionid(String parent_unionid) {
                this.parent_unionid = parent_unionid;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getUnionid() {
                return unionid;
            }

            public void setUnionid(String unionid) {
                this.unionid = unionid;
            }

            public Object getSubscribe() {
                return subscribe;
            }

            public void setSubscribe(Object subscribe) {
                this.subscribe = subscribe;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public Object getSubscribe_time() {
                return subscribe_time;
            }

            public void setSubscribe_time(Object subscribe_time) {
                this.subscribe_time = subscribe_time;
            }

            public String getPrivilege() {
                return privilege;
            }

            public void setPrivilege(String privilege) {
                this.privilege = privilege;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getGroupid() {
                return groupid;
            }

            public void setGroupid(Object groupid) {
                this.groupid = groupid;
            }

            public Object getTagid_list() {
                return tagid_list;
            }

            public void setTagid_list(Object tagid_list) {
                this.tagid_list = tagid_list;
            }

            public Object getSubscribe_scene() {
                return subscribe_scene;
            }

            public void setSubscribe_scene(Object subscribe_scene) {
                this.subscribe_scene = subscribe_scene;
            }

            public String getWithdraw_money() {
                return withdraw_money;
            }

            public void setWithdraw_money(String withdraw_money) {
                this.withdraw_money = withdraw_money;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public int getAdopt_friendpop_num() {
                return adopt_friendpop_num;
            }

            public void setAdopt_friendpop_num(int adopt_friendpop_num) {
                this.adopt_friendpop_num = adopt_friendpop_num;
            }

            public int getTotal_friendpop_num() {
                return total_friendpop_num;
            }

            public void setTotal_friendpop_num(int total_friendpop_num) {
                this.total_friendpop_num = total_friendpop_num;
            }

            public int getAdd_goods_num() {
                return add_goods_num;
            }

            public void setAdd_goods_num(int add_goods_num) {
                this.add_goods_num = add_goods_num;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getTao_session() {
                return tao_session;
            }

            public void setTao_session(String tao_session) {
                this.tao_session = tao_session;
            }

            public Object getTao_session_valid_time() {
                return tao_session_valid_time;
            }

            public void setTao_session_valid_time(Object tao_session_valid_time) {
                this.tao_session_valid_time = tao_session_valid_time;
            }

            public String getTkl_type() {
                return tkl_type;
            }

            public void setTkl_type(String tkl_type) {
                this.tkl_type = tkl_type;
            }

            public IsDeleteBean getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(IsDeleteBean is_delete) {
                this.is_delete = is_delete;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public String getUnread_msg_count() {
                return unread_msg_count;
            }

            public void setUnread_msg_count(String unread_msg_count) {
                this.unread_msg_count = unread_msg_count;
            }

            public int getWithdraw_apply() {
                return withdraw_apply;
            }

            public void setWithdraw_apply(int withdraw_apply) {
                this.withdraw_apply = withdraw_apply;
            }

            public static class IsDeleteBean {
                /**
                 * type : Buffer
                 * data : [0]
                 */

                private String type;
                private List<Integer> data;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public List<Integer> getData() {
                    return data;
                }

                public void setData(List<Integer> data) {
                    this.data = data;
                }
            }
        }
    }
}
