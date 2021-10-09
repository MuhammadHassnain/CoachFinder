

import { createStore } from "vuex";

import coachModule from './modules/coaches/index.js';
import requestModule from './modules/requests/index.js'
import authModule from './modules/auth/index.js'

const store = createStore({
    modules: { coaches: coachModule , requests: requestModule, auth: authModule },

    

});

export default store;