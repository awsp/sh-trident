<script>
  import {Router} from "@roxi/routify";
  import {routes} from "../.routify/routes";
</script>

<style global lang="scss">
  .debug {
    position: relative;

    &:hover {
      box-shadow: 0 0 3px 0 rgba(255, 0, 0, 0.8);

      & > header.metadata {
        display: block;
        top: -20px;
        left: -1px;
        padding: 1px 12px;
        color: white;
        background: rgba(255, 0, 0, 0.8);
        border-top-left-radius: 4px;
        border-top-right-radius: 4px;
      }
    }
  }

  header.metadata{
    font-size: 0.75rem;
    position: absolute !important;
    display: none;
  }

  .page {
    padding: 10px 20px;
  }
</style>

<Router {routes} config={{useHash: true}}/>