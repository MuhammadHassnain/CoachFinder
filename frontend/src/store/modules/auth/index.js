import mutations from './mutations'
import getters from './getters'
import actions from './actions'

export default {
      state() {
        return {
            UserInfo:  {
                    id: null,
                    email: null,
                    firstName: null,
                    lastName: null,
                    description: null,
                    hourlyRate: null,
                    areas: null
                },
            loggedInUser: {
                token: null,
                tokenExpiration: null,
                userId: null
            }
        }
    },
    mutations,
    actions,
    getters
}