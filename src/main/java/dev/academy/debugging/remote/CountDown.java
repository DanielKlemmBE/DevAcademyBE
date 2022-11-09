package dev.academy.debugging.remote;

public class CountDown {

    private final int m_initialCurrentCount;
    private int m_currentCount;

    public CountDown(int countFrom){
        m_initialCurrentCount = countFrom;
        m_currentCount = m_initialCurrentCount;
    }

    public int countDown(){
        if (m_currentCount > 0){
            m_currentCount = m_currentCount -1;
        }
        else {
            m_currentCount = m_initialCurrentCount;
        }
        return m_currentCount;
    }
}
