package demo.service;

import demo.model.CurrentPosition;

public interface PositionService {

    public void processPositionInfo(long id, CurrentPosition currentPosition, boolean exportPositionsToMessaging);
}
