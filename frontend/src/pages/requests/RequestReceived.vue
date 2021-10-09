<template>
  <div>
    <base-dialog :show="!!error" title="Error" @close="error = null">
      <p>{{ error }}</p>
    </base-dialog>
    <section>
      <base-card>
        <h2>
          Request Received
        </h2>
        <base-spinner v-if="isLoading"> </base-spinner>

        <ul v-else-if="hasRequest && !isLoading">
          <request-item
            v-for="req in requests"
            :key="req.id"
            :email="req.email"
            :message="req.message"
          ></request-item>
        </ul>
        <h3 v-else>
          You don't have any request
        </h3>
      </base-card>
    </section>
  </div>
</template>

<script>
import RequestItem from '../../components/requests/RequestItem.vue';
import BaseDialog from '../../components/UI/BaseDialog.vue';
export default {
  components: { RequestItem, BaseDialog },

  data() {
    return {
      isLoading: false,
      error: null
    };
  },
  methods: {
    async loadRequests() {
      this.isLoading = true;
      try {
        await this.$store.dispatch('requests/loadRequest');
      } catch (error) {
        this.error = error.message || 'Failed to fetch Requests';
      }
      this.isLoading = false;
    }
  },
  created() {
    this.loadRequests();
  },
  computed: {
    requests() {
      return this.$store.getters['requests/requests'];
    },
    hasRequest() {
      return this.$store.getters['requests/hasRequest'];
    }
  }
};
</script>

<style scoped>
header {
  text-align: center;
}

ul {
  list-style: none;
  margin: 2rem auto;
  padding: 0;
  max-width: 30rem;
}

h3 {
  text-align: center;
}
</style>
