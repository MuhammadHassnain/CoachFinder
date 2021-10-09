<template>
  <div>
    <base-dialog :show="!!error" @close="HandleError">
      <template v-slot:header> <h2>Error while Login</h2></template>
      <template v-slot:default>
        <p>{{ error }}</p></template
      ></base-dialog
    >
    <base-dialog :show="isLoading" fixed>
      <template v-slot:header> <h2>Authenticating</h2></template>
      <base-spinner></base-spinner>
    </base-dialog>
    <base-card>
      <form @submit.prevent="submitForm">
        <div class="form-control">
          <label for="email">E-mail</label>
          <input type="email" id="email" v-model.trim="email" />
        </div>
        <div class="form-control">
          <label for="password">Password</label>
          <input type="password" id="password" v-model.trim="password" />
        </div>
        <p v-if="!formIsValid">
          Please enter valid Email and Password ( must be atleast 6 digit long )
        </p>
        <base-button>{{ switchButtonCaption }}</base-button>
        <base-button type="button" mode="flat" @click="changeFormType">{{
          switchInsteadButtonCaption
        }}</base-button>
      </form>
    </base-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
      formIsValid: true,
      formType: 'login',
      isLoading: false,
      error: ''
    };
  },

  computed: {
    switchButtonCaption() {
      if (this.formType === 'login') {
        return 'Login';
      }
      return 'SignUp';
    },
    switchInsteadButtonCaption() {
      if (this.formType === 'login') {
        return 'SignUp Instead';
      }
      return 'Login Instead';
    }
  },
  methods: {
    async submitForm() {
      this.formIsValid = true;
      this.isLoading = true;

      if (
        this.email === '' ||
        !this.email.includes('@') ||
        this.password.length < 6
      ) {
        this.formIsValid = false;
        console.log('Hello');
        return;
      } else {
        this.formIsValid = true;
      }
      try {
        if (this.formType === 'login') {
          await this.$store.dispatch('login', {
            username: this.email,
            password: this.password
          });
          await this.$store.dispatch('getAuthDetail');
        } else {
          await this.$store.dispatch('signup', {
            email: this.email,
            password: this.password
          });

          //after successfully signup automatically login

          await this.$store.dispatch('login', {
            username: this.email,
            password: this.password
          });

          await this.$store.dispatch('getAuthDetail');
        }
        const redirectURL = '/' + (this.$route.query.redirect || 'coaches');
        this.$router.replace(redirectURL);
      } catch (error) {
        console.log(error);
        this.error = error.message;
      }

      this.isLoading = false;
    },
    changeFormType() {
      this.formIsValid = true;
      if (this.formType === 'login') {
        this.formType = 'signup';
      } else {
        this.formType = 'login';
      }
    },
    HandleError() {
      this.error = null;
    }
  }
};
</script>

<style>
form {
  margin: 1rem;
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
