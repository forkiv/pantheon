/*
 * Copyright 2018 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package tech.pegasys.pantheon.ethereum.eth.sync.state;

import tech.pegasys.pantheon.ethereum.core.BlockHeader;
import tech.pegasys.pantheon.ethereum.eth.manager.ChainState;
import tech.pegasys.pantheon.ethereum.eth.manager.EthPeer;

import com.google.common.base.MoreObjects;

public class SyncTarget {

  private final EthPeer peer;
  private BlockHeader commonAncestor;

  public SyncTarget(final EthPeer peer, final BlockHeader commonAncestor) {
    this.peer = peer;
    this.commonAncestor = commonAncestor;
  }

  public EthPeer peer() {
    return peer;
  }

  public BlockHeader commonAncestor() {
    return commonAncestor;
  }

  public void setCommonAncestor(final BlockHeader commonAncestor) {
    this.commonAncestor = commonAncestor;
  }

  @Override
  public String toString() {
    final ChainState chainState = peer.chainState();
    return MoreObjects.toStringHelper(this)
        .add(
            "height",
            (chainState.getEstimatedHeight() == 0 ? "?" : chainState.getEstimatedHeight()))
        .add("td", chainState.getBestBlock().getTotalDifficulty())
        .add("peer", peer)
        .toString();
  }
}