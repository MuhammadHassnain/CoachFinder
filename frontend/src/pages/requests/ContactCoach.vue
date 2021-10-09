<template>
  <div>
    <base-dialog :show="!!error" title="Error" @close="error = null">
      <p>{{ error }}</p>
    </base-dialog>
    <form @submit.prevent="submitForm">
      <div>
        <label for="email">Your Email</label>
        <input type="email" id="email" v-model.trim="email" />
      </div>
      <div>
        <label for="message">Your Email</label>
        <textarea id="message" row="5" v-model.trim="message" />
      </div>

      <p class="error" v-if="!formIsValid">Please Enter The Valid Input</p>
      <div class="actions">
        <base-button>Send message</base-button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      error: null,
      email: 'test@test.com',
      message: 'message',
      formIsValid: true
    };
  },
  methods: {
    submitForm() {
      this.formIsValid = true;
      if (
        this.email === '' ||
        !this.email.includes('@') ||
        this.message === ''
      ) {
        this.formIsValid = false;
        return;
      }

      this.submitRequest();
    },
    async submitRequest() {
      try {
        await this.$store.dispatch('requests/contactCoach', {
          email: this.email,
          message: this.message,
          coachId: this.$route.params.id
        });

        this.$router.replace('/coaches/' + this.$route.params.id);
      } catch (error) {
        this.error = 'Failed to  Request now. Try Again';
      }
    }
  }
};
</script>

<style scoped>
form {
  margin: 1rem;
  border: 1px solid #ccc;
  border-radius: 12px;
  padding: 1rem;
}

.form-control {
  margin: 0.5rem 0;
}

label {
  font-weight: bold;
  margin-bottom: 0.5rem;
  display: block;
}

input,
textarea {
  display: block;
  width: 100%;
  font: inherit;
  border: 1px solid #ccc;
  padding: 0.15rem;
}

input:focus,
textarea:focus {
  border-color: #3d008d;
  background-color: #faf6ff;
  outline: none;
}

.errors {
  font-weight: bold;
  color: red;
}

.actions {
  text-align: center;
  margin-top: 2rem;
}
</style>
