package org.deeplearning4j.parallelism.factory;

import org.deeplearning4j.nn.api.Model;
import org.deeplearning4j.nn.conf.WorkspaceMode;
import org.deeplearning4j.parallelism.ParallelWrapper;
import org.deeplearning4j.parallelism.trainer.Trainer;

/**
 * Creates {@link Trainer}
 * instances for use with {@link ParallelWrapper}
 *
 * @author Adam Gibson
 */
public interface TrainerContext {


    /**
     * Initialize the context
     * @param model
     * @param args the arguments to initialize with (maybe null)
     */
    void init(Model model, Object... args);

    /**
     * Create a {@link Trainer}
     * based on the given parameters
     * @param threadId the thread id to use for this worker
     * @param model the model to start the trainer with
     * @param rootDevice the root device id
     *               or not
     * @param wrapper the wrapper instance to use with this trainer (this refernece is needed
     *                for coordination with the {@link ParallelWrapper} 's {@link org.deeplearning4j.optimize.api.IterationListener}
     * @return the created training instance
     */
    Trainer create(int threadId, Model model, int rootDevice, ParallelWrapper wrapper,
                    WorkspaceMode workspaceMode, int averagingFrequency);


    /**
     * This method is called at averagingFrequency
     *
     * @param originalModel
     * @param models
     */
    void finalizeRound(Model originalModel, Model... models);

    /**
     * This method is called
     *
     * @param originalModel
     * @param models
     */
    void finalizeTraining(Model originalModel, Model... models);
}
