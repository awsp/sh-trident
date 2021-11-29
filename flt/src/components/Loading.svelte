<style lang="scss">
  $width: 15px;

  div.loading-grid {
    display: inline-block;
    margin: 10px;
    padding: 6px;
  }

  div.loading {
    display: grid;
    grid-template-columns: $width;
    grid-template-rows: $width;
    grid-gap: 2px;
    transform: rotateZ(45deg);
  }

  div.square {
    width: $width;
    height: $width;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    text-align: center;
    transition: all 0.3s;
    animation: spin 2s cubic-bezier(0, 0.8, 0, 0.8) infinite;
    box-shadow: 0 0 0 1px #ddd;
  }

  div.loading-tl {
    grid-row: 1;
    grid-column: 1;
    background: rgba(255, 232, 254, 0.6);
    border: 1px solid rgba(255, 232, 254, 0.9);
    /*animation-delay: 0s;*/
  }

  div.loading-tr {
    grid-row: 1;
    grid-column: 2;
    background: rgba(232, 243, 255, 0.6);
    border: 1px solid rgba(232, 243, 255, 0.9);
    /*animation-delay: .7s;*/
  }

  div.loading-bl {
    grid-row: 2;
    grid-column: 1;
    background: rgba(247, 250, 175, 0.6);
    border: 1px solid rgba(247, 250, 175, 0.9);
    /*animation-delay: 0.4s;*/
  }

  div.loading-br {
    grid-row: 2;
    grid-column: 2;
    background: rgba(250, 175, 206, 0.6);
    border: 1px solid rgba(250, 175, 206, 0.9);
    /*animation-delay: 0.6s;*/
  }

  @keyframes spin {
    from {
      transform: rotate3d(1, 1, 0, 0deg);
    }
    to {
      transform: rotate3d(1, 1, 0, 360deg);
    }
  }
</style>

<div class="loading-grid">
  <div class="loading">
    <div class="square loading-tl"></div>
    <div class="square loading-tr"></div>
    <div class="square loading-bl"></div>
    <div class="square loading-br"></div>
  </div>
</div>