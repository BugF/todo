<template>
    
</template>
<script>
export default {
  name: "App",
  data() {
    return {
      isCollapse: false
    };
  },
  mounted() {},
  methods: {
    getMenu() {},
    buildMenu() {
      this.$post("/api/wx/test", {}).then(response => {
        console.log(response);
      });
    }
  }
};
</script>
