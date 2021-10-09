<template>
  <div>
    <section>
      <base-card>
        <h2>{{ fullName }}</h2>
        <h3>${{ rate }}</h3>
      </base-card>
    </section>
    <section>
      <base-card>
        <header>
          <h2>Interest reach out now</h2>
          <base-button :to="contactLink" :link="true">Contact</base-button>
          <router-view />
        </header>
      </base-card>
    </section>
    <section>
      <base-card>
        <base-badge
          v-for="area in areas"
          :key="area"
          :type="area"
          :title="area"
        >
        </base-badge>
        <p>{{ description }}</p>
      </base-card>
    </section>
  </div>
</template>

<script>
export default {
  props: { id: String },

  data() {
    return {
      selectedCoach: null
    };
  },
  computed: {
    fullName() {
      return this.selectedCoach.firstName + ' ' + this.selectedCoach.lastName;
    },
    areas() {
      return this.selectedCoach.areas;
    },
    rate() {
      return this.selectedCoach.hourlyRate;
    },
    description() {
      return this.selectedCoach.description;
    },

    contactLink() {
      console.log(
        '🚀 ----------------------------------------------------------------------------------------'
      );
      console.log(
        '🚀 : file: CoachDetail.vue : line 53 : contactLink : this.$route.path ',
        this.$route.path
      );
      console.log(
        '🚀 ----------------------------------------------------------------------------------------'
      );
      return this.$route.path + '/contact';
    }
  },
  created() {
    console.log(this.$store.getters['coaches/coaches']);
    const all_coaches = this.$store.getters['coaches/coaches'];

    console.log('🚀 --------------------------------------------');
    console.log('🚀 : file: CoachDetail.vue : line 61 : id', this.id);
    console.log('🚀 --------------------------------------------');

    this.selectedCoach = all_coaches.find(coach => {
      return coach.id == this.id;
    });
    console.log(this.selectedCoach);
  }
};
</script>
