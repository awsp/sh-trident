<script>

</script>

<style lang="scss">
  :global {
    .button-set {
      display: flex;

      button {
        &:active, &:focus {
          z-index: 10;
        }
      }

      button:first-child {
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
      }

      button:nth-child(n + 2):not(:last-child) {
        border-radius: 0;
        margin-left: -1px;
      }

      button:last-child {
        border-top-left-radius: 0;
        border-bottom-left-radius: 0;
        margin-left: -1px;
      }
    }
  }
</style>

<!-- HTML -->
<div class="button-set">
  <slot/>
</div>