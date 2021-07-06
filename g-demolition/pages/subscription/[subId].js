import React, {useEffect, useState} from "react";
import {useRouter} from "next/router";

import styles from "../../styles/Subscription.module.scss";
import {Card, Elevation, Tree} from "@blueprintjs/core";
import {generateTreeData, updateTree, updateTreeNode} from "../../helpers/tree-helper";
import FeedList from "../../components/subscription/FeedList";
import {getFeeds} from "../../helpers/feed-helper";
import Controls from "../../components/Controls";

const Subscription = () => {
  const router = useRouter();
  const {subId} = router.query;

  const [subscription, setSubscription] = useState(null);
  const [treeData, setTreeData] = useState(null);
  const [feedData, setFeedData] = useState(null);
  const [feedHeading, setFeedHeading] = useState('');
  const [focusId, setFocusId] = useState(null);
  const [loadSubscription, setLoadSubscription] = useState(false);

  const getSubscription = async () => {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/subscription/${subId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    return response.json();
  };

  useEffect(() => {
    if (!router.isReady) return;
    getSubscription().then(subscriptionData => {
      setSubscription(subscriptionData);
      setTreeData(generateTreeData(subscriptionData.focuses));
      if (loadSubscription) {
        setLoadSubscription(false);
      }
    });
  }, [router.isReady, loadSubscription]);

  const handleNodeClick = (node, path) => {
    if (node && node.id) {
      setFeedHeading(node.label);
      setFocusId(node.id);
      setTreeData(updateTreeNode(treeData, path, 'isSelected', true, false));
      getFeeds(node.id).then(feedData => {
        setFeedData(feedData);
      });
    }
  };

  const handleNodeExpand = (node) => {
    setTreeData(updateTree(treeData, node, 'isExpanded', true));
  };

  const handleNodeCollapse = (node) => {
    setTreeData(updateTree(treeData, node, 'isExpanded', false));
  };

  return (
    <Card className={styles.subscriptionContainer} elevation={Elevation.ONE}>
      <aside>
        <section>
          <h1>{subscription && subscription.name}</h1>
          {treeData && <Tree contents={treeData}
                             onNodeClick={handleNodeClick}
                             onNodeExpand={handleNodeExpand}
                             onNodeCollapse={handleNodeCollapse}/>}
        </section>
        <Controls>
          <Controls.AddFocus callback={setLoadSubscription}/>
          <Controls.RemoveFocus focusId={focusId} callback={setLoadSubscription}/>
          <Controls.History/>
          <Controls.Settings/>
        </Controls>
      </aside>
      <main>
        <h1>{feedHeading}</h1>
        {feedData ? <FeedList feeds={feedData}/> : <div>Loading</div>}
      </main>
    </Card>
  );
};

export default Subscription;