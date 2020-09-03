<template>
  <v-row>
    <v-col cols="12">
      <v-row
          align="start"
          justify="center"
      >
        <v-card
            :hover="hover"
            max-width="344"
            class="ma-5"
            v-for="i in 15"
            v-bind:key="i"
        >
          <v-card-title>Hello world {{ i }}.</v-card-title>

          <v-card-text>
            Visit ten places on our planet that are undergoing the biggest changes today.
          </v-card-text>

          <v-card-actions>
            <v-btn icon color="blue">
              <v-icon>mdi-cog</v-icon>
            </v-btn>
            <v-btn icon color="red">
              <v-icon>mdi-lock</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "Projects.vue",
  data() {
    return {
      hover: true
    }
  }
}
</script>

<style scoped>

</style>