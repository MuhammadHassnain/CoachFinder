export default {


    requests(state) {
        console.log("🚀 -------------------------------------------------------")
        console.log("🚀 : file: getters.js : line 5 : requests : state", state)
        console.log("🚀 -------------------------------------------------------")
      
        return state.requests;
    }
    ,
    hasRequest(state,getters) {
        console.log("🚀 --------------------------------------------------------------")
        console.log("🚀 : file: getters.js : line 13 : hasRequest : getters", getters)
        console.log("🚀 --------------------------------------------------------------")
        console.log("🚀 ----------------------------------------------------------")
        console.log("🚀 : file: getters.js : line 13 : hasRequest : state", state)
        console.log("🚀 ----------------------------------------------------------")

        return getters.requests && getters.requests.length > 0;
    }
};