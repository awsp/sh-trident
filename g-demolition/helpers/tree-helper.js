export function generateTreeData(dataArr) {
  const childNodes = dataArr?.map(node => {
    return {
      id: node.id,
      icon: 'tag',
      label: node.name,
      isSelected: false
    };
  });

  return [{
    id: 0,
    hasCaret: true,
    isExpanded: true,
    label: 'Focuses',
    icon: 'folder-close',
    childNodes
  }];
}

export function updateTree(treeData, node, key, value) {
  return treeData.map(treeNode => {
    if (treeNode.id === node.id) {
      return {
        ...treeNode,
        [key]: value
      }
    }
    return treeNode;
  });
}

export function updateTreeNode(treeData, path, key, value, alternativeValue) {
  const index = path[0];
  const childIndex = path[1];

  treeData[index].childNodes = treeData[index].childNodes.map((treeNode, i) => {
    if (childIndex === i) {
      return {
        ...treeNode,
        [key]: value
      };
    }
    return alternativeValue !== undefined ? {
      ...treeNode,
      [key]: alternativeValue
    } : treeNode;
  });
  return treeData;
}
