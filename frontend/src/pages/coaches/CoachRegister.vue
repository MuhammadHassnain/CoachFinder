<template>
  <div>
    <base-spinner v-if="isLoading"></base-spinner>
    <base-dialog :show="!!error" @close="error = null">
      {{ error }}
    </base-dialog>
    <base-card>
      <h2>Register A Coach</h2>
      <coach-form @coach-data="saveCoachData"></coach-form>
    </base-card>
  </div>
</template>

<script>
import CoachForm from '../../components/coaches/CoachForum.vue';
export default {
  components: { CoachForm },

  data() {
    return {
      error: null,
      isLoading: false
    };
  },

  methods: {
    async saveCoachData(coachData) {
      this.isLoading = true;
      try {
        await this.$store.dispatch('coaches/registerCoach', coachData);
        this.isLoading = false;
        this.$router.replace('/coaches');
      } catch (error) {
        this.isLoading = false;
        this.error = 'Failed To Registe!! Please Try Again';
      }
    }
  }
};
</script>

<style scoped></style>
