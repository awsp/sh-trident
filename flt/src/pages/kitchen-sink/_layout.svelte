<script>
  import {url} from '@roxi/routify';
</script>

<style lang="scss">
  html, body {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
  }

  nav {
    width: 250px;
    margin-right: 20px;

    ul {
      padding: 0;
      margin: 0;

      li {
        width: 100%;
        padding: 3px 8px;
        margin-bottom: 6px;
        border: 1px solid transparent;

        &:hover {
          border: 1px solid #ccc;
        }
      }
    }
  }

  @media (max-width: 420px) {
    .container {
      flex-direction: column;
    }
  }

  .container {
    padding: 10px;
    background: #f6f5f5;
    width: 100%;
    height: 100%;
    display: flex;

    .content {
      flex: 1;
    }
  }
</style>

<!-- HTML -->
<div class="container">
  <nav>
    <ul>
      <li><a href="./buttons" use:$url>Buttons</a></li>
      <li><a href="./button-set" use:$url>Button Set</a></li>
      <li><a href="./badge" use:$url>Badge</a></li>
      <li><a href="./calendar" use:$url>Calendar</a></li>
    </ul>
  </nav>
  <div class="content">
    <slot/>
  </div>
</div>