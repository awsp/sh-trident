<script>
  export let theme = "";

  function getTheme(theme) {
    switch (theme) {
      case "alert":
        return "alert";
      case "primary":
        return "primary";
      case "lavandula":
        return "lavandula";
      default:
        return "default";
    }
  }
</script>

<style lang="scss">
  @import "../base.scss";

  .badge {
    padding: 1px 5px * $phi;
    font-size: 0.8rem;
    border-radius: 4px;
    cursor: default;
    user-select: none;
  }
</style>

<!-- HTML -->
<span class="badge {getTheme(theme)}">
  <slot />
</span>