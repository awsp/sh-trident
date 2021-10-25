import {useEffect, useState} from "react";
import Feed from "./Feed";

import styles from "../../styles/Focus.module.scss";
import Skeleton from "../Skeleton";
import {Card} from "@blueprintjs/core";
import {getFeeds} from "../../helpers/feed-helper";

const Focus = ({focus}) => {
  const [feeds, setFeeds] = useState(null);

  useEffect(() => {
    getFeeds(focus.id).then(data => {
      setFeeds(data);
    });
    return () => {
      setFeeds(null);
    }
  }, [focus]);

  return <Card className={styles.focus}>
    Focus: {focus.name}
    <section>
      {feeds ?
        feeds.length > 0 ?
          feeds.map(feed => <Feed key={`feed-${feed.id}`} feed={feed}/>) :
          <div>No feeds</div> :
        <Skeleton/>}
    </section>
  </Card>;
};

export default Focus;