<template>
  <v-row
      align="center"
      justify="center"
  >
    <v-col
        cols="12"
        sm="8"
        md="4"
        class="my-10"
    >
      <v-card class="elevation-12">
        <v-toolbar
            color="primary"
            dark
            flat
        >
          <v-toolbar-title>Login</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field
                label="用户名"
                name="login"
                prepend-icon="mdi-account"
                type="text"
                v-model="form.username"
            ></v-text-field>

            <v-text-field
                id="password"
                label="密码"
                name="password"
                prepend-icon="mdi-lock"
                type="password"
                v-model="form.password"
            ></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="login">
            <v-icon left>mdi-key</v-icon>
            Login
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import config from '//variables'
export default {
  name: "Login",
  data() {
    return {
      form: {
        username: '',
        password: '',
      }
    }
  },
  methods: {
    login() {
      this.$http.post(config.api_host, this.form)
    }
  }
}
</script>
