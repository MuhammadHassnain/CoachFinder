<template>
  <div>
    <base-dialog :show="!!error" title="Error" @close="error = null">
      <p>{{ error }}</p>
    </base-dialog>
    <section>
      <base-card>
        <coach-filter @filter-changed="changeFilter"></coach-filter>
      </base-card>
    </section>
    <section>
      <base-card>
        <div class="controls">
          <base-button mode="outline" link @click="loadCoaches(true)"
            >Refresh</base-button
          >
          <base-button v-if="!isLoggedIn" link to="/auth?redirect=register"
            >Login to Register as Coach</base-button
          >
          <base-button
            v-if="!isCoach && !isLoading && isLoggedIn"
            link
            to="/register"
            >Register as Coach</base-button
          >
        </div>
        <div v-if="isLoading">
          <base-spinner></base-spinner>
        </div>
        <ul v-else-if="hasCoaches && !isLoading">
          <coach-item
            v-for="coach in filteredCoaches"
            :key="coach.id"
            :id="coach.id"
            :first-name="coach.firstName"
            :last-name="coach.lastName"
            :areas="coach.areas"
            :rate="coach.hourlyRate"
          >
            {{ coach.firstName }}
          </coach-item>
        </ul>
        <h3 v-else>NO coach found</h3>
      </base-card>
    </section>
  </div>
</template>

<script>
import CoachItem from '../../components/coaches/CoachItem.vue';
import CoachFilter from '../../components/coaches/CoachFilter.vue';
export default {
  components: { CoachFilter, CoachItem },
  data() {
    return {
      isLoading: false,
      error: null,
      filters: {
        frontend: true,
        backend: true,
        career: true
      }
    };
  },
  methods: {
    changeFilter(newFilter) {
      this.filters = newFilter;
    },
    async loadCoaches(refresh = false) {
      this.isLoading = true;
      try {
        await this.$store.dispatch('coaches/loadCoaches', {
          forceRefresh: refresh
        });
      } catch (error) {
        this.error = error.message || 'Something Went Wrong';
      }

      this.isLoading = false;
    }
  },
  created() {
    this.loadCoaches();
  },

  computed: {
    isCurrentUser() {
      return this.$store.getters['coaches/isCurrentUser'];
    },

    isCoach() {
      return this.$store.getters['coaches/isCoach'];
    },
    isLoggedIn() {
      return this.$store.getters['isAuthenticated'];
    },
    filteredCoaches() {
      const allCoaches = this.$store.getters['coaches/coaches'];

      const selectedCoaches = allCoaches.filter(coach => {
        if (this.filters.frontend && coach.areas.includes('frontend')) {
          return true;
        }
        if (this.filters.backend && coach.areas.includes('backend')) {
          return true;
        }
        if (this.filters.career && coach.areas.includes('career')) {
          return true;
        }
        return false;
      });
      return selectedCoaches;
    },
    hasCoaches() {
      return this.$store.getters['coaches/hasCoaches'];
    }
  }
};
</script>

<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.controls {
  display: flex;
  justify-content: space-between;
}
</style>
