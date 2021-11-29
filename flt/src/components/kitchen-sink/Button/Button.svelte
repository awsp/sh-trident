<script>
  export let rounded = false;
  export let small = false;
  export let large = false;
  export let full = false;
  export let almostFlat = false;
  export let disabled = false;
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
  .small {
    font-size: 0.9rem;
    padding: 3px $phi * 6px;
  }

  .large {
    font-size: 1.3em;
    padding: 5px $phi * 10px;
  }

  .full {
    display: block;
    width: 100%;
    padding: 8px $phi * 16px;
  }

  .rounded {
    border-radius: 28px;
    padding: 3px $phi * 18px;
    &.large {
      padding: 5px $phi * 18px;
    }
  }

  .almost-flat {
    background: linear-gradient(to bottom, rgba(252, 250, 248, 1) 0%, rgba(242, 240, 243, 1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
    &:active {
      background: linear-gradient(to bottom, rgba(242, 240, 243, 1) 0%, rgba(252, 250, 248, 1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
    }
  }

  .disabled {
    pointer-events: none;
    color: #999;
  }

  button {
    font-family: 'Open Sans', sans-serif;
    background: rgba(252, 250, 248, 1);
    font-size: 1rem;
    color: #383131;
    border-radius: 0.25rem;
    padding: 4px $phi * 8px;
    transition: all 0.15s;
    outline: none;
    border: 1px solid #c2c2c2;
    box-shadow: 0 0 1px 0 #8a8686;
    text-shadow: 0 1px 0 rgb(255 255 255 / 5%);
    user-select: none;
    cursor: default;

    &:active {
      background: #f0f0f0;
    }

    &:hover {
      border: 1px solid #8b8686;
      color: #282121;
    }

    &:focus {
      border-color: #197de1;
      box-shadow: 0 0 0 2px rgb(25 125 225 / 50%);
    }
  }

  @media (max-width: 640px) {
  }
</style>

<button on:click
        class:rounded
        class:small
        class:full
        class:large
        class:almost-flat={almostFlat}
        class:disabled
        class={getTheme(theme)}
>
  <slot/>
</button>