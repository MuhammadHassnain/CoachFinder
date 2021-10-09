import { createRouter, createWebHistory } from "vue-router";
import CoachDetail from './pages/coaches/CoachDetail.vue'
import CoachesList from './pages/coaches/CoachesList.vue'
import CoachRegister from './pages/coaches/CoachRegister.vue'
import ContactCoach from './pages/requests/ContactCoach.vue'
import ReqeustReceived from './pages/requests/RequestReceived.vue'
import NotFound from './pages/NotFound.vue'
import UserAuth from './pages/auth/UserAuth.vue'
import store from './store/index.js'

const router = createRouter({

    history: createWebHistory(),
    routes: [
        { path: '/', redirect: '/coaches' },
        { path: '/coaches', component: CoachesList },
        {
            path: '/coaches/:id',
            component: CoachDetail,
            props: true,
            children: [
                { path: 'contact', component: ContactCoach }
            ]
        },
        { path: '/register', component: CoachRegister , meta: {requireAuth:true}},
        { path: '/request', component: ReqeustReceived , meta: {requireAuth:true}},
        { path: '/auth' , component: UserAuth,  meta: {requireUnAuth:true}},
        { path: '/:fuckyou(.*)', component: NotFound }
    ]
});

router.beforeEach(function (to,from,next) {
    if (to.meta.requireAuth && !store.getters.isAuthenticated) {
        next('/auth');
    } else if (to.meta.requireUnAuth && store.getters.isAuthenticated) {
        next('/coaches')
    }else{
        next();
    }
})
export default router;