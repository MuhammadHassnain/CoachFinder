export default {
        userId(state) {
            return state.UserInfo.userId;
    },

    loggedInUser(state) {
        return state.loggedInUser
    },

    UserInfo(state) {
        return state.UserInfo;
    },

    token(state) {
        return state.loggedInUser.token;
    },

    isAuthenticated(state) {
        return !!state.loggedInUser.token;
    }
}