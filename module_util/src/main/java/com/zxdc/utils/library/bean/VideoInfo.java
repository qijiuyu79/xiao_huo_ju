package com.zxdc.utils.library.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/3/3.
 */

public class VideoInfo extends BaseBean {

    private VideoBean data;

    public VideoBean getData() {
        return data;
    }

    public void setData(VideoBean data) {
        this.data = data;
    }

    public static class VideoBean implements Serializable{
        private int episodeCount;
        private int followCount;
        private boolean followSerial;
        private boolean followUser;
        private int id;
        private String introduction;
        private boolean onLine;
        private int seconds;
        private int serialId;
        private boolean thumbEpisode;
        private String videourl;
        private int nextEpisodeId;
        private int prevEpisodeId;
        private int userId;
        private String userImg;
        private int commentCount;
        private int thumbCount;
        private String commentCountDesc;
        private String followCountDesc;
        private String thumbCountDesc;
        private int currentEpisode;

        public int getEpisodeCount() {
            return episodeCount;
        }

        public void setEpisodeCount(int episodeCount) {
            this.episodeCount = episodeCount;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public boolean isFollowSerial() {
            return followSerial;
        }

        public void setFollowSerial(boolean followSerial) {
            this.followSerial = followSerial;
        }

        public boolean isFollowUser() {
            return followUser;
        }

        public void setFollowUser(boolean followUser) {
            this.followUser = followUser;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public boolean isOnLine() {
            return onLine;
        }

        public void setOnLine(boolean onLine) {
            this.onLine = onLine;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getSerialId() {
            return serialId;
        }

        public void setSerialId(int serialId) {
            this.serialId = serialId;
        }

        public boolean isThumbEpisode() {
            return thumbEpisode;
        }

        public void setThumbEpisode(boolean thumbEpisode) {
            this.thumbEpisode = thumbEpisode;
        }

        public String getVideourl() {
            return videourl;
        }

        public void setVideourl(String videourl) {
            this.videourl = videourl;
        }

        public int getNextEpisodeId() {
            return nextEpisodeId;
        }

        public void setNextEpisodeId(int nextEpisodeId) {
            this.nextEpisodeId = nextEpisodeId;
        }

        public int getPrevEpisodeId() {
            return prevEpisodeId;
        }

        public void setPrevEpisodeId(int prevEpisodeId) {
            this.prevEpisodeId = prevEpisodeId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getThumbCount() {
            return thumbCount;
        }

        public void setThumbCount(int thumbCount) {
            this.thumbCount = thumbCount;
        }

        public String getCommentCountDesc() {
            return commentCountDesc;
        }

        public void setCommentCountDesc(String commentCountDesc) {
            this.commentCountDesc = commentCountDesc;
        }

        public String getFollowCountDesc() {
            return followCountDesc;
        }

        public void setFollowCountDesc(String followCountDesc) {
            this.followCountDesc = followCountDesc;
        }

        public String getThumbCountDesc() {
            return thumbCountDesc;
        }

        public void setThumbCountDesc(String thumbCountDesc) {
            this.thumbCountDesc = thumbCountDesc;
        }

        public int getCurrentEpisode() {
            return currentEpisode;
        }

        public void setCurrentEpisode(int currentEpisode) {
            this.currentEpisode = currentEpisode;
        }
    }
}
