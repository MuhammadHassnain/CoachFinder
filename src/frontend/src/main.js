import { createApp } from 'vue';
import router from './router.js'
import App from './App.vue'
import store from './store/index.js'

import BaseCard from './components/UI/BaseCard.vue'
import BaseButton from './components/UI/BaseButton.vue'
import BaseBadge from './components/UI/BaseBadge.vue'
import BaseDialog from './components/UI/BaseDialog.vue';
import BaseSpinner from './components/UI/BaseSpinner.vue'

const app = createApp(App);
app.component('base-card', BaseCard);
app.component('base-button', BaseButton);
app.component('base-badge', BaseBadge);
app.component('base-spinner', BaseSpinner);
app.component('base-dialog', BaseDialog);
app.use(store);
app.use(router);

console.log(store);

app.mount('#app');