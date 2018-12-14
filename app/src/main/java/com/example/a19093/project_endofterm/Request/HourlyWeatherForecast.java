package com.example.a19093.project_endofterm.Request;

import java.util.List;

public class HourlyWeatherForecast {
    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.4052887","tz":"+8.00"}
         * update : {"loc":"2018-12-14 19:46","utc":"2018-12-14 11:46"}
         * status : ok
         * hourly : [{"cloud":"8","cond_code":"100","cond_txt":"晴","dew":"-19","hum":"72","pop":"0","pres":"1028","time":"2018-12-14 22:00","tmp":"-4","wind_deg":"190","wind_dir":"南风","wind_sc":"1-2","wind_spd":"3"},{"cloud":"10","cond_code":"101","cond_txt":"多云","dew":"-20","hum":"74","pop":"0","pres":"1031","time":"2018-12-15 01:00","tmp":"-5","wind_deg":"171","wind_dir":"南风","wind_sc":"1-2","wind_spd":"6"},{"cloud":"56","cond_code":"101","cond_txt":"多云","dew":"-19","hum":"74","pop":"0","pres":"1029","time":"2018-12-15 04:00","tmp":"-5","wind_deg":"94","wind_dir":"东风","wind_sc":"1-2","wind_spd":"10"},{"cloud":"55","cond_code":"101","cond_txt":"多云","dew":"-17","hum":"71","pop":"0","pres":"1029","time":"2018-12-15 07:00","tmp":"-7","wind_deg":"82","wind_dir":"东风","wind_sc":"1-2","wind_spd":"3"},{"cloud":"33","cond_code":"502","cond_txt":"霾","dew":"-17","hum":"67","pop":"0","pres":"1029","time":"2018-12-15 10:00","tmp":"-3","wind_deg":"116","wind_dir":"东南风","wind_sc":"1-2","wind_spd":"1"},{"cloud":"57","cond_code":"502","cond_txt":"霾","dew":"-16","hum":"61","pop":"0","pres":"1027","time":"2018-12-15 13:00","tmp":"0","wind_deg":"174","wind_dir":"南风","wind_sc":"1-2","wind_spd":"10"},{"cloud":"89","cond_code":"502","cond_txt":"霾","dew":"-10","hum":"57","pop":"0","pres":"1025","time":"2018-12-15 16:00","tmp":"1","wind_deg":"186","wind_dir":"南风","wind_sc":"1-2","wind_spd":"2"},{"cloud":"97","cond_code":"502","cond_txt":"霾","dew":"-11","hum":"54","pop":"0","pres":"1026","time":"2018-12-15 19:00","tmp":"-1","wind_deg":"185","wind_dir":"南风","wind_sc":"1-2","wind_spd":"10"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private List<HourlyBean> hourly;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public static class BasicBean {
            /**
             * cid : CN101010100
             * location : 北京
             * parent_city : 北京
             * admin_area : 北京
             * cnty : 中国
             * lat : 39.90498734
             * lon : 116.4052887
             * tz : +8.00
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean {
            /**
             * loc : 2018-12-14 19:46
             * utc : 2018-12-14 11:46
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class HourlyBean {
            /**
             * cloud : 8
             * cond_code : 100
             * cond_txt : 晴
             * dew : -19
             * hum : 72
             * pop : 0
             * pres : 1028
             * time : 2018-12-14 22:00
             * tmp : -4
             * wind_deg : 190
             * wind_dir : 南风
             * wind_sc : 1-2
             * wind_spd : 3
             */

            private String cloud;
            private String cond_code;
            private String cond_txt;
            private String dew;
            private String hum;
            private String pop;
            private String pres;
            private String time;
            private String tmp;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public String getCond_code() {
                return cond_code;
            }

            public void setCond_code(String cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getDew() {
                return dew;
            }

            public void setDew(String dew) {
                this.dew = dew;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }
    }
}
